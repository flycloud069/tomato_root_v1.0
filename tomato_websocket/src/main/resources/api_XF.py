from flask import Flask, jsonify,request, g
from sparkai.llm.llm import ChatSparkLLM, ChunkPrintHandler
from sparkai.core.messages import ChatMessage
import requests
import json

app = Flask(__name__)

@app.route('/')
def index():
    return "Hello, World!"

@app.route('/api' ,methods=['Post'])
def api_data2():
    # 获取 JSON 请求体
    data = request.get_json()
    print(data)
    # 从请求体中提取参数
    messages = data.get('messages')
    messages = {
               "model": "doubao-seed-1-6-250615",
               "messages": [
                   {
                       "role": "user",
                       "content": messages
                   }
               ],
    "thinking":{
         "type":"disabled"
     }
             }
    url='https://ark.cn-beijing.volces.com/api/v3/chat/completions'
    """
    发送带请求头的POST请求

    参数:
    url (str): 请求的URL
    headers (dict, optional): 请求头字典
    payload (dict, optional): 请求体数据
    timeout (int, optional): 请求超时时间(秒)

    返回:
    dict: 包含响应状态码、响应头和响应体的字典
    """
    try:
        # 设置默认请求头
        default_headers = {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer 9a30f984-2275-4bfa-9bfb-bf00105b90a6'
        }

        # 处理请求体
        data = json.dumps(messages) if messages else None

        # 发送POST请求
        response = requests.post(
            url,
            headers=default_headers,
            data=data,
        )

        # 检查响应状态码
        response.raise_for_status()  # 如果状态码不是200,抛出HTTPError
        print(response.json())
        # 解析响应
        try:
            response_data = response.json()
        except json.JSONDecodeError:
            response_data = response.text

        return response_data["choices"][0]["message"]["content"]

    except requests.exceptions.HTTPError as http_err:
        print(f'HTTP错误发生: {http_err}')
        return None
    except requests.exceptions.ConnectionError as conn_err:
        print(f'连接错误发生: {conn_err}')
        return None
    except requests.exceptions.Timeout as timeout_err:
        print(f'请求超时: {timeout_err}')
        return None
    except requests.exceptions.RequestException as req_err:
        print(f'其他错误发生: {req_err}')
        return None
    return None

@app.route('/api2' ,methods=['Post'])
def api_data():

    # 获取 JSON 请求体
    data = request.get_json()
    print(data)
    # 从请求体中提取参数
    messages = data.get('messages')
    # 从请求体中提取参数
    temperature = data.get('temperature')
    # 从请求体中提取参数
    top_k = data.get('top_k')
    max_tokens= data.get('max_tokens')
     
    #调用这个链了。这将返回一个字典 - 来自 LLM 的响应在键中answer
    print('返回的消息：',messages)
    
    #星火认知大模型Spark Max的URL值，其他版本大模型URL值请前往文档（https://www.xfyun.cn/doc/spark/Web.html）查看
    SPARKAI_URL = 'wss://spark-api.xf-yun.com/v1.1/chat'
    #星火认知大模型调用秘钥信息，请前往讯飞开放平台控制台（https://console.xfyun.cn/services/bm35）查看
    SPARKAI_APP_ID = 'ae7ffcf8'
    SPARKAI_API_SECRET = 'YzkzOTQ4M2Q0MzNiYTM3NmRlNzk1MDRm'
    SPARKAI_API_KEY = 'c2676162063b6792b37b39340e814550'
    #星火认知大模型Spark Max的domain值，其他版本大模型domain值请前往文档（https://www.xfyun.cn/doc/spark/Web.html）查看
    SPARKAI_DOMAIN = 'general'

    spark = ChatSparkLLM(
        spark_api_url=SPARKAI_URL,
        spark_app_id=SPARKAI_APP_ID,
        spark_api_key=SPARKAI_API_KEY,
        spark_api_secret=SPARKAI_API_SECRET,
        spark_llm_domain=SPARKAI_DOMAIN,
        temperature=temperature,
        max_tokens=max_tokens,
        top_k=top_k,
        streaming=False,
    )
    messages = [ChatMessage(
        role="user",
        content=messages
    )]
    handler = ChunkPrintHandler()
    a = spark.generate([messages], callbacks=[handler])
    print(123,a.generations[0][0].text)
    return a.generations[0][0].text
 
if __name__ == '__main__':
#    app.run(debug=True , host= 'localhost' ,port=8080 , threaded=True)
    app.run(debug=True , host= 'localhost' ,port=8080 )