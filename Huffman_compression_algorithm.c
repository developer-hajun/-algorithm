#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

#define MAX_ELEMENT 200

typedef struct Node{
    char character;
    int frequency;
    struct Node* left;
    struct Node* right;
}Node;

typedef struct Queue {
    Node* nodes[MAX_ELEMENT];
    int size;
}Queue;

typedef struct Pair{
    char first;
    char* second;
}Pair;

Node* createNode(char character, int frequency) {
    Node* node = (Node*)malloc(sizeof(Node));
    node->character = character;
    node->frequency = frequency;
    node->left = NULL;
    node->right = NULL;
    return node;
}

void initQueue(Queue* pq) {
    pq->size = 0;
}
int queueSize(Queue* pq) {
    return pq->size;
}

void insert(Queue* pq, Node* node) {
    pq->nodes[pq->size++] = node;
    for (int i = pq->size - 1; i > 0; i--) {
        if (pq->nodes[i]->frequency > pq->nodes[i - 1]->frequency) {
            Node* temp = pq->nodes[i];
            pq->nodes[i] = pq->nodes[i - 1];
            pq->nodes[i - 1] = temp;
        }
        else {
            break;
        }
    }
}
Node* removes(Queue* pq) {
    if (pq->size == 0) return NULL;
    return pq->nodes[--pq->size];
}
char Acode[4];
char Ccode[4];
char Tcode[4];
char Gcode[4];
void find(char* str, Node* node) {
    if (node->character != '-') {
        switch (node->character) {
        case 'A':
            strcpy(Acode, str);
            break;
        case 'C':
            strcpy(Ccode, str);
            break;
        case 'T':
            strcpy(Tcode, str);
            break;
        case 'G':
            strcpy(Gcode, str);
            break;
        }
        return;
    }

    if (node->left != NULL) {
        char newStr0[12];
        snprintf(newStr0, sizeof(newStr0), "%s0", str);
        find(newStr0, node->left);
    }
    if (node->right != NULL) {
        char newStr1[12];
        snprintf(newStr1, sizeof(newStr1), "%s1", str);
        find(newStr1, node->right);
    }
}


int compare(const void* a, const void* b) {
    const Pair* pairA = (const Pair*)a;
    const Pair* pairB = (const Pair*)b;

    size_t lenA = strlen(pairA->second);
    size_t lenB = strlen(pairB->second);

    return  -((lenA > lenB) - (lenA < lenB));
}


void replace(char* str, const char* oldSubStr, char newChar) {
    char* pos, temp[100];
    int index = 0;
    int oldSubStrLen = strlen(oldSubStr);

    while ((pos = strstr(str, oldSubStr)) != NULL) {
        strncpy(temp, str, pos - str);
        temp[pos - str] = '\0';
        temp[pos - str] = newChar;
        strcpy(temp + (pos - str) + 1, pos + oldSubStrLen);
        strcpy(str, temp);
    }
}

int main() {
    FILE* fp = fopen("Huffman_input.txt", "r");
    int ch;
    int A = 0;
    int T = 0;
    int G = 0;
    int C = 0;

    do {
        ch = fgetc(fp);
        switch (ch) {
        case 65:
            A++;
            break;
        case 67:
            C++;
            break;
        case 84:
            T++;
            break;
        case 71:
            G++;
            break;
        }
    } while (ch != EOF);

    Queue pq;
    initQueue(&pq);
    insert(&pq, createNode('A', A));
    insert(&pq, createNode('T', T));
    insert(&pq, createNode('C', C));
    insert(&pq, createNode('G', G));
    while (queueSize(&pq) >= 2) {
        Node* leftNode = removes(&pq);
        Node* rightNode = removes(&pq);
        Node* newNode = createNode('-', leftNode->frequency + rightNode->frequency);
        newNode->left = leftNode;
        newNode->right = rightNode;
        insert(&pq, newNode);
    }
    Node* finallyNode = removes(&pq);
    char newStr[12] = "";
    find(newStr, finallyNode);
    Pair pairs[4];
    pairs[0].first = 'A';
    pairs[0].second = Acode;
    pairs[3].first = 'C';
    pairs[3].second = Ccode;
    pairs[1].first = 'T';
    pairs[1].second = Tcode;
    pairs[2].first = 'G';
    pairs[2].second = Gcode;
    printf("Huffman code (");
    for(int i=0;i<4;i++){
      printf("'%c' = %s",pairs[i].first,pairs[i].second);
      if(i<3){
        printf(", ");
      }
      else{
      printf(")\n");
      }
    }
    size_t size = sizeof(pairs) / sizeof(pairs[0]);
    qsort(pairs, size, sizeof(Pair), compare);

    char target[] = "10110010001110101010100";
    for (int i = 0; i < 4; i++) {
        replace(target, pairs[i].second, pairs[i].first);
    }
    printf("%s\n",target);
}
