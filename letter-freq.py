import plotly.plotly as py
import plotly.graph_objs as go

cipherfile = open('Ctext-1', 'r')
ciphertext = str(cipherfile.read())
cipherfile.close()
alphabet   = ["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"]

def histogram(alphabet, text):
    let_count = []
    for x in alphabet:
        let_count.append(text.count(str(x))/4.92)
    return let_count

print histogram(alphabet, ciphertext)

data = [
        go.Bar(
            x = alphabet,
            #y = histogram(alphabet, ciphertext)
            y = [8.167, 1.492, 2.782, 4.253, 12.702, 2.228, 2.015, 6.094, 6.966, 0.153, 0.772, 4.025, 2.406, 6.749, 7.507, 1.929, 0.095, 5.987, 6.327, 9.056, 2.758, 0.978, 2.361, 0.150, 1.974, 0.074]
            )
        ]
plot_url = py.plot(data, filename='basic-bar')


