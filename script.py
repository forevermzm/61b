import urllib, re, os, sys
from BeautifulSoup import BeautifulSoup

def findTagA(url, directory):
    if not os.path.exists(directory):
        os.makedirs(directory)

    doc = urllib.urlopen(url).read()
    soup = BeautifulSoup(doc)
    tags = soup.findAll('a')
    for tag in tags:
        text = tag['href']
        if text.find('?') < 0 and text.find('/') != 0:
            if text.find('/') < 0:
                data = urllib.urlretrieve(url + text, directory + '/' + text)
            else :
                findTagA(url + text, directory + '/' + text)


findTagA(sys.argv[1], sys.argv[2])