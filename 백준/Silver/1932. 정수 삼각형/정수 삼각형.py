# 5 // 30

# 7
# 3 8
# 8 1 0
# 2 7 4 4
# 4 5 2 6 5

n = int(input())
tree = []

for _ in range(n):
    tree.append(list(map(int, input().split())))

for i in range(1, n):
    for j in range(len(tree[i])):
        if j == 0:
            tree[i][j] = tree[i][j] + tree[i-1][j]
        elif j == len(tree[i])-1:
            tree[i][j] = tree[i][j] + tree[i-1][j-1]
        else:
            tree[i][j] = max(tree[i-1][j-1], tree[i-1][j]) + tree[i][j]
        
print(max(tree[n-1]))