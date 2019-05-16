//
//  main.c
//  parsestring
//
//  Created by Yuqi Cao on 5/7/19.
//  Copyright © 2019 GT. All rights reserved.
//
/**
 * In this problem, I'm asked to write a solution that a string pointer is passed in
 * and we need to return an integer indicating how many "BALLOON" there are in the string.
 */

#include <stdio.h>

// you can write to stdout for debugging purposes, e.g.
// printf("this is a debug message\n");
#include <string.h>

int solution(char *S) {
    // write your code in C99 (gcc 6.2.0)
    //construct an array for look up chars
    //size_t length = strlen(S);
    int i = 0;
    
    int dict[5];
    for (int j = 0; j < 5; j++) {
        dict[j] = 0;
    }
    while(i < strlen(S)) {
        char current = S[i];
//        printf("%c\n", current);
        int temp = current - 'A';
        switch(temp) {
            case 0:
                dict[0]++;
                break;
            case 1:
                dict[1]++;
                break;
            case 11:
                dict[2]++;
                break;
            case 13:
                dict[3]++;
                break;
            case 14:
                dict[4]++;
                break;
            default:
                break;
        }
        i++;
        
    }
    
    dict[2] = dict[2] / 2;
    dict[4] = dict[4] / 2;
    
    int result = dict[0];

    
    for (int j = 1; j < 5; j++) {

        if (dict[j] < result) {
            result = dict[j];
        }
    }
    return (int)result;
    
}

int main(int argc, const char * argv[]) {
    // insert code here...    
    char *ptr = "HEYTHEREBALLOONOOOONBALLLL";
    int result = solution(ptr);
    printf("%d\n", result);
    return 0;
}
