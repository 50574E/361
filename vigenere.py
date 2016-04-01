alphabet = "abcdefghijklmnopqrstuvwxyz"

def caesar(letter, shift):
    return alphabet[(alphabet.find(letter)+shift)%len(alphabet)]

def vigenere(pw, text):
    newText = ""
    counter = 0
    for x in text:
        if x != " ":
            newText += caesar(x, alphabet.find(pw[counter%len(pw)]))
            counter += 1
        else:
            newText += " "
    return newText

encoded_file = open("Ctext-2", "r")
ctext = str(encoded_file.read())
password = 'wnu'#'rqi'

#print vigenere('wnu', 'xuk')

for x in range(16):
    print vigenere(password, ctext)
    password = 'a' + password
    print
