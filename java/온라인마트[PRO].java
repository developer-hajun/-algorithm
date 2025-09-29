import java.util.*;

class UserSolution {
    static class Product {
        int id;
        int amount;
        int category;
        int company;
        boolean sold;

        public Product(int i, int a, int c, int cc) {
            id = i;
            amount = a;
            category = c;
            company = cc;
            sold = true;
        }
    }

    Map<Integer, Product> products = new HashMap<>();
    int[][] size;
    int[][] discount;
    TreeSet<Product>[][] groupSet;

    public void init() {
        products.clear();
        size = new int[6][6];
        discount = new int[6][6];
        groupSet = new TreeSet[6][6];

        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                int finalI = i;
                int finalJ = j;
                groupSet[i][j] = new TreeSet<>((a, b) -> {
                    if (a.amount != b.amount) return Integer.compare(a.amount, b.amount);
                    return Integer.compare(a.id, b.id);
                });
            }
        }
    }

    public int sell(int mID, int mCategory, int mCompany, int mPrice) {
        int stored = mPrice + discount[mCategory][mCompany];

        Product product = new Product(mID, stored, mCategory, mCompany);
        products.put(mID, product);

        groupSet[mCategory][mCompany].add(product);
        size[mCategory][mCompany]++;
        return size[mCategory][mCompany];
    }

    public int closeSale(int mID) {
        Product product = products.get(mID);
        if (product == null || !product.sold) return -1;

        product.sold = false;
        size[product.category][product.company]--;

        return product.amount - discount[product.category][product.company];
    }

    public int discount(int mCategory, int mCompany, int mAmount) {
        discount[mCategory][mCompany] += mAmount;

        TreeSet<Product> set = groupSet[mCategory][mCompany];
        while (!set.isEmpty() && set.first().amount <= discount[mCategory][mCompany]) {
            Product p = set.pollFirst(); // 가장 싼 제품
            if (p != null && p.sold) {
                p.sold = false;
                size[mCategory][mCompany]--;
            }
        }

        return size[mCategory][mCompany];
    }

    Solution.RESULT show(int mHow, int mCode) {
        Solution.RESULT res = new Solution.RESULT();

        PriorityQueue<Product> pq = new PriorityQueue<>((a, b) -> {
            int aReal = a.amount - discount[a.category][a.company];
            int bReal = b.amount - discount[b.category][b.company];

            if (aReal != bReal) return bReal - aReal;
            return b.id - a.id;
        });

        final int MAX_PICK_PER_GROUP = 5;

        for (int category = 1; category <= 5; category++) {
            for (int company = 1; company <= 5; company++) {
                if (mHow == 1 && category != mCode) continue;
                if (mHow == 2 && company != mCode) continue;

                TreeSet<Product> set = groupSet[category][company];
                int picked = 0;
                for (Product p : set) {
                    if (picked == MAX_PICK_PER_GROUP) break;
                    if (p == null || !p.sold) continue;

                    pq.offer(p);
                    if (pq.size() > 5) pq.poll();
                    picked++;
                }
            }
        }

        ArrayList<Product> resultList = new ArrayList<>(pq);
        resultList.sort((a, b) -> {
            int aReal = a.amount - discount[a.category][a.company];
            int bReal = b.amount - discount[b.category][b.company];

            if (aReal != bReal) return aReal - bReal;
            return a.id - b.id;
        });

        res.cnt = resultList.size();
        for (int i = 0; i < res.cnt; i++) {
            res.IDs[i] = resultList.get(i).id;
        }

        return res;
    }
}
