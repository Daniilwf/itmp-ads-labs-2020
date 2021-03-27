#include <iostream>
 
int main()
{
    int tempmin = 1000000000;
    int j = -1;
    int k = 0;
    int N;
    int x;
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);
    std::cin >> N;
    int* a = new int[N];
    for (int i = 0; i < N; i++) {
        std::ios_base::sync_with_stdio(false);
        std::cin.tie(NULL);
        std::cin >> x;
        if (x == 1) {
            j++;
            std::ios_base::sync_with_stdio(false);
            std::cin.tie(NULL);
            std::cin >> a[j];
            if (tempmin > a[j]) {
                tempmin = a[j];
                k = j;
            }
        }
        if (x == 2) {
            if (k == j) {
                tempmin = 1000000000;
                for (int z = 0; z < j; z++)
                    if (tempmin > a[z]) {
                        tempmin = a[z];
                        k = z;
                    }
            }
            a[j] = 0;
            j--;
        }
        if (x == 3) {
            std::ios_base::sync_with_stdio(false);
            std::cout << tempmin << "\n";
        }
    }
    delete[] a;
}