#include "string_util.h"

int compare_strings(char a[], char b[]) {
    int c = 0;
    while (a[c] == b[c]) {
        if (a[c] == '\0' || b[c] == '\0')
            break;
        c++;
    }
    if (a[c] == '\0' && b[c] == '\0')
        return 0;
    else
        return -1;
}