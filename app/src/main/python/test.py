from boilerpy3 import extractors
import requests
from bs4 import BeautifulSoup
from io import BytesIO
from PIL import Image






def text(url):                  #본문

    extractor = extractors.ArticleExtractor()

    text = extractor.get_content_from_url(url)

    return text

def title(url):                 #제목

    header = {'User-Agent':'Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36'}

    web = BeautifulSoup(requests.get(url, allow_redirects=False, headers=header).text, 'html.parser')

    title = web.find("meta", property="og:title")['content']

    return title

def description(url):           #요약내용

    header = {'User-Agent':'Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36'}

    web = BeautifulSoup(requests.get(url, allow_redirects=False, headers=header).text, 'html.parser')

    description = web.find('meta', property="og:description")['content']


    return description

def Url(url):                   #URL

    Url= url

    return Url

def image(url):                 #이미지

    header = {'User-Agent':'Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36'}

    web = BeautifulSoup(requests.get(url, allow_redirects=False, headers=header).text, 'html.parser')

    image_url = web.find("meta", property="og:image")['content']

    imagedown = Image.open('dsa.jpg')(BytesIO(requests.get(image_url, headers=header).content))

    return imagedown

