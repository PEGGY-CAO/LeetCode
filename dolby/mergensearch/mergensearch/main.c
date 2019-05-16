//
//  main.c
//  mergensearch
//
//  Created by Yuqi Cao on 5/6/19.
//  Copyright © 2019 GT. All rights reserved.
//
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

#include <stdio.h>



void merge2arr(int A[], int l, int m, int r) {
    int i, j, k;
    int ln = m - l + 1;
    int rn = r - m;
    int L[ln], R[rn];
    for (i = 0; i < ln; i++) {
        L[i] = A[l + i];
    }
    for (j = 0; j < rn; j++) {
        R[j] = A[m + 1 + j];
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

void mergeSort(int A[], int l, int r) {
    if (l < r) {
        int m = l + (r - l) / 2;
        
        
        mergeSort(A, l, m);
        mergeSort(A, m + 1, r);
        
        merge2arr(A, l, m, r);
        
    }
}

int binarySearch(int A[], int l, int r) {
    
    int m = l + (r - l) / 2;
    
    int num = A[m];
    //base case
    if (num == 1)
        return m;
    
    if (l < r) {
        if (num < 1) {
            return binarySearch(A, m+1, r);
        } else {
            return binarySearch(A, l, m);
        }
    }
    return m;
    
}

int solution(int A[], int N) {
    //merge sort A first then do binary search find the smallest positive integer
    mergeSort(A, 0, N - 1);
    //corner case
    if (A[0] > 1 || A[N - 1] <= 0)
        return 1;
    //then do binary search looking for the first positive number's position
    int index = binarySearch(A, 0, N - 1);
    if (A[index] != 1) {
        return 1;
    }
    
    //right now A[index]==1
    while(index + 1 < N) {
        if (A[index + 1] - A[index] <= 1) {
            index++;
        } else {
            return A[index] + 1;
        }
    }
    
    return A[N - 1] + 1;
}



int main(int argc, const char * argv[]) {
    // insert code here...
    printf("Hello, World!\n");
    int A[] = {8, 5,1,3,6,4,1,2,3, 6, 6, 6,6};
    int length = sizeof(A)/sizeof(int);
    int res = solution(A, length);
    printf("%d\n", res);
    return 0;
}
