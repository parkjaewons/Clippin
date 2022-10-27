import requests
from bs4 import BeautifulSoup
from io import BytesIO
from boilerpy3 import extractors

def main():
    url = "https://n.news.naver.com/mnews/article/001/0013534442?sid=102"
    header = {'User-Agent':'Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36'}

    # html parser
    web = BeautifulSoup(requests.get(url, allow_redirects=False, headers=header).text, 'html.parser')

    # 제목
    title = web.find("meta", property="og:title")['content']

    return title

def text():
    extractor = extractors.ArticleExtractor()

    # From a URL
    content = extractor.get_content_from_url('https://n.news.naver.com/mnews/article/001/0013534442?sid=102')

    return content