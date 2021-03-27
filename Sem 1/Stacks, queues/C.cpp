#include <list>
#include <iostream>
 
int main()
{
    int top = -1;
    int bot = 0;
    int y;
    int x;
    int N;
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);
    std::cin >> N;
    int *stack = new int[N];
    for (int i = 0; i < N; i++) {
        std::ios_base::sync_with_stdio(false);
        std::cin.tie(NULL);
        std::cin >> x;
        if (x == 1) {
            std::ios_base::sync_with_stdio(false);
            std::cin.tie(NULL);
            std::cin >> y;
            stack[++top] = y;
        }
        if (x == 4) {
            std::cin >> y;
            for (int j = bot; j < N; j++) {
                if (y == stack[j]) {
                    std::ios_base::sync_with_stdio(false);
                    std::cout << j - bot << "\n";
                    break;
                }
            }
        }
        if (x == 2)
            stack[bot++]=0;
        if (x == 3)
            stack[top--]=0;
        if (x == 5) {
            std::ios_base::sync_with_stdio(false);
            std::cout << stack[bot] << "\n";
        }
    }
    delete[] stack;
}
 