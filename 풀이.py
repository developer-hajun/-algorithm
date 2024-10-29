import requests
import csv
import time
from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import TimeoutException, NoSuchElementException  # 예외 가져오기
from concurrent.futures import ThreadPoolExecutor

# 아이폰 15 프로 128GB 제품만 크롤링하는 함수
def process_page(page_number, options):
    # ChromeDriver 실행
    driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()), options=options)

    try:
        # 번개장터 아이폰 프로 15 128GB 검색 결과 페이지 URL
        url = f"https://m.bunjang.co.kr/search/products?order=score&page={page_number}&q=%EC%97%90%EC%96%B4%ED%8C%9F%ED%94%84%EB%A1%9C1"
        driver.get(url)

        # 각 상품 링크 추출
        WebDriverWait(driver, 20).until(
            EC.presence_of_all_elements_located((By.CSS_SELECTOR, 'a[data-pid]'))
        )
        product_links = [link.get_attribute('href') for link in driver.find_elements(By.CSS_SELECTOR, 'a[data-pid]')]

        items_data = []

        for link_href in product_links:
            try:
                # 상대 경로 링크를 절대 경로로 변환
                if not link_href.startswith("http"):
                    product_url = f"https://m.bunjang.co.kr{link_href}"
                else:
                    product_url = link_href
                driver.get(product_url)

                # 상품 제목 추출 및 '128' 포함 여부 확인
                WebDriverWait(driver, 20).until(
                    EC.visibility_of_element_located((By.CSS_SELECTOR, ".ProductSummarystyle__Name-sc-oxz0oy-3.dZBHcg"))
                )
                title = driver.find_element(By.CSS_SELECTOR, ".ProductSummarystyle__Name-sc-oxz0oy-3.dZBHcg").text
                if '1' not in title:
                    #print(f"Skipped product without '1' in title: {title}")
                    continue  # '128'이 없으면 다음 상품으로

                # 가격 크롤링 시도
                try:
                    WebDriverWait(driver, 40).until(
                        EC.visibility_of_element_located((By.CSS_SELECTOR, ".ProductSummarystyle__Price-sc-oxz0oy-5.cspsrp"))
                    )
                    price = driver.find_element(By.CSS_SELECTOR, ".ProductSummarystyle__Price-sc-oxz0oy-5.cspsrp").text
                    price = price.replace("\u20a9", "").replace(",", "").replace("원", "").strip()
                    print(f"Price found: {price}")  # 가격 출력
                except (NoSuchElementException, TimeoutException):
                    print(f"Price not found for product: {product_url}")
                    continue  # 가격이 없으면 다음 상품으로 넘어갑니다.

                # 상품 상태 크롤링 (새상품, 사용감 있음 등)
                try:
                    WebDriverWait(driver, 20).until(
                        EC.visibility_of_element_located((By.CSS_SELECTOR, ".ProductSummarystyle__Value-sc-oxz0oy-21.eLyjky"))
                    )
                    condition = driver.find_element(By.CSS_SELECTOR, ".ProductSummarystyle__Value-sc-oxz0oy-21.eLyjky").text
                except (NoSuchElementException, TimeoutException):
                    print(f"Condition not found for product: {product_url}")
                    condition = "Unknown"  # 상태 정보가 없을 때 기본값 설정

                # 등록 기간 크롤링
                try:
                    WebDriverWait(driver, 20).until(
                        EC.visibility_of_element_located((By.CSS_SELECTOR, ".ProductSummarystyle__Status-sc-oxz0oy-11.fMQseI"))
                    )
                    registration_period = driver.find_element(By.CSS_SELECTOR, ".ProductSummarystyle__Status-sc-oxz0oy-11.fMQseI").text
                except (NoSuchElementException, TimeoutException):
                    print(f"Registration period not found for product: {product_url}")
                    registration_period = "Unknown"

                # 조회수 크롤링
                try:
                    view_count = driver.find_elements(By.CSS_SELECTOR, ".ProductSummarystyle__Status-sc-oxz0oy-11.fMQseI")[1].text
                except (NoSuchElementException, TimeoutException, IndexError):
                    print(f"View count not found for product: {product_url}")
                    view_count = "Unknown"

                # 찜수 크롤링
                try:
                    like_count = driver.find_elements(By.CSS_SELECTOR, ".ProductSummarystyle__Status-sc-oxz0oy-11.fMQseI")[2].text
                except (NoSuchElementException, TimeoutException, IndexError):
                    print(f"Like count not found for product: {product_url}")
                    like_count = "Unknown"

                items_data.append([condition, price, registration_period, view_count, like_count])

            except TimeoutException:
                print(f"Timeout occurred when processing product: {product_url}")
                continue

        return items_data
    finally:
        driver.quit()


# 크롤링 옵션 설정 (Headless 모드)
chrome_options = Options()
chrome_options.add_argument("--headless")
chrome_options.add_argument("user-agent=Mozilla/5.0")

# CSV 파일 초기화
csv_file = open('airpots_pro_1.csv', 'w', newline='', encoding='utf-8')
csv_writer = csv.writer(csv_file)
csv_writer.writerow(['Condition', 'Price', 'Like Count', 'View Count', 'Registration Period'])  # 헤더 추가

# 페이지 범위 설정 (1~5 페이지)
page_range = range(1, 50)

# 병렬 처리로 빠르게 크롤링 진행
with ThreadPoolExecutor(max_workers=5) as executor:
    futures = {executor.submit(process_page, page, chrome_options): page for page in page_range}
    for future in futures:
        items_data = future.result()
        # 크롤링된 가격 출력
        for item in items_data:
            csv_writer.writerow(item)

csv_file.close()
