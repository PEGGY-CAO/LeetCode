#include <stdio.h>
#include <stdlib.h>


/**
    * demo task
    * write a function: int solution(int A[], int N);
    * that, given an array A of N integers, 
    * returns the smallest positive integer (greater than 0) that does not occur in A
    * For example, given A=[1,3,6,4,1,2], the function should return 5
    * Given A = [1,2,3], the function returns 4
    * Given A = [-1, -3], the functio returns 1
    * Write an efficient algorithm for the following assumptions:
    *   - N is an integer within the range [1...100,000]
    *   - each element of array A is an integer within the range [-1,000,000 ...1,000,000]
    */

void mergeSort(int A[], int l, int r);
void merge2arr(int A[], int l, int m, int r);

int solution(int A[], int N) {
    //merge sort A first then do binary search find the smallest positive integer
    mergeSort(A, 0, N - 1);
    return 1;
}

void mergeSort(int A[], int l, int r) {
    if (l < r) {
        int m = l + (l - r) / 2;
        

        mergeSort(A, l, m);
        mergeSort(A, m + 1, r);

        merge2arr(A, l, m, r);

    }
}

void merge2arr(int A[], int l, int m, int r) {
    int i, j, k;
    int ln = m - k + 1;
    int rn = r - m;
    int L[ln], R[rn];
    for (i = 0; i < ln; i++) {
        L[i] = A[l + i];
    }
    for (j = 0; j < ln; j++) {
        R[j] = A[l + m + j];
    }
    i = 0;
    j = 0;
    k = l;
    while (i < ln && j < rn) {
        if (L[i] <= R[j]) {
            A[k] = L[i];
            i++;
            
        } else {
            A[k] = R[j];
            j++;
        }
        k++;
    }
    while (i < ln) {
        A[k] = L[i];
        i++;
        k++;
    }
    while (j < rn) {
        A[k] = R[j];
        j++;
        k++;
    }
}


int main() {
    int A[6] = {1,3,6,4,1,2};
    
    int res = solution(A, 6);
    printf("%d", res);
    return 1;
}