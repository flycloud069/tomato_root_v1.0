from flask import Flask, jsonify,request, g
from sparkai.llm.llm import ChatSparkLLM, ChunkPrintHandler
from sparkai.core.messages import ChatMessage

app = Flask(__name__)

@app.route('/')
def index():
    return "Hello, World!"
 
@app.route('/api' ,methods=['Post'])
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