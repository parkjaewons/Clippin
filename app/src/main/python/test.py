from boilerpy3 import extractors
import requests
from bs4 import BeautifulSoup
from io import BytesIO



def text(url):
    # request error를 피하기 위한 header 설정
    header = {'User-Agent':'Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36'}

    # html parser
    web = BeautifulSoup(requests.get(url, allow_redirects=False, headers=header).text, 'html.parser')

    # 제목
    title = web.find("meta", property="og:title")['content']

    # 요약 내용
    description = web.find('meta', property="og:description")['content']

    extractor = extractors.ArticleExtractor()

# From a URL
    text = extractor.get_content_from_url(url)

    return text

def title(url):
    # request error를 피하기 위한 header 설정
    header = {'User-Agent':'Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36'}

    # html parser
    web = BeautifulSoup(requests.get(url, allow_redirects=False, headers=header).text, 'html.parser')

    # 제목
    title = web.find("meta", property="og:title")['content']

    return title

def description(url):
    # request error를 피하기 위한 header 설정
    header = {'User-Agent':'Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36'}

    # html parser
    web = BeautifulSoup(requests.get(url, allow_redirects=False, headers=header).text, 'html.parser')

    # 요약 내용
    description = web.find('meta', property="og:description")['content']


    return description

def Url(url):
    # request error를 피하기 위한 header 설정
    header = {'User-Agent':'Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36'}

    # html parser
    web = BeautifulSoup(requests.get(url, allow_redirects=False, headers=header).text, 'html.parser')
    Url= url

    return Url