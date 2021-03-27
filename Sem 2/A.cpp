#include <iostream>
#include <algorithm>
 
using namespace std;
 
long long build(long long* t, long long* a, int v, int tl, int tr) {
    if (tl == tr) {
        t[v] = a[tl];
        return a[tl];
    }
    else {
        int tm = (tl + tr) / 2;
        build(t, a, v * 2, tl, tm);
        build(t, a, v * 2 + 1, tm + 1, tr);
        t[v] = t[v * 2] + t[v * 2 + 1];
        return t[v];
    }
}
 
long long sum(long long* t, int v, int tl, int tr, int l, int r) {
    if (tr < l || tl > r) {
        return 0;
    }
    if (l <= tl && r >= tr)
        return t[v];
    int tm = (tl + tr) / 2;
    return sum(t, v * 2, tl, tm, l, min(r, tm))
        + sum(t, v * 2 + 1, tm + 1, tr, max(l, tm + 1), r);
}
 
long long* update(long long* t, int v, int tl, int tr, int pos, int new_val) {
    if (tl == tr)
        t[v] = new_val;
    else {
        int tm = (tl + tr) / 2;
        if (pos <= tm)
            update(t, v * 2, tl, tm, pos, new_val);
        else
            update(t, v * 2 + 1, tm + 1, tr, pos, new_val);
        t[v] = t[v * 2] + t[v * 2 + 1];
        return t;
    }
}
 
long long* building(long long* a, int n) {
    long long* t = new long long[4 * n];
    build(t, a, 1, 0, n - 1);
    return t;
}
 
int main(void)
{
    int n;
    int m;
    cin >> n >> m;
    int temp;
    int index;
    long v;
    int l;
    int r;
    long long* a = new long long[n];
    for (int i = 0; i < n; i++)
        cin >> a[i];
    long long* t = new long long[4 * n];
    t = building(a, n);
    for (int i = 0; i < m; i++) {
        cin >> temp;
        if (temp == 1) {
            cin >> index >> v;
            t = update(t, 1, 0, n - 1, index, v);
        }
        else {
            cin >> l >> r;
            cout << sum(t, 1, 0, n - 1, l, r - 1) << "\n";
        }
    }
}