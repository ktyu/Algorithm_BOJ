def recursive(arr, i, l, password):

    # ������ ���
    if len(password) == l:
        vowel = password.count('a') + password.count('e') + password.count('i') + password.count('o') + password.count('u')
        consonant = len(password) - vowel
        if vowel >= 1 and consonant >= 2:
            print(password)
        return

    # �ȵǴ� ���
    elif len(password) >= l:
        return

    # ������ ȣ��
    if i < len(arr):
        recursive(arr, i+1, l, password+arr[i])  # �������� ���
        recursive(arr, i+1, l, password)  # �������� ���x


l = int(input().strip().split()[0])
arr = input().strip().split()
arr.sort()
recursive(arr, 0, l, "")
