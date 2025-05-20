FROM openjdk:11-jre-slim

# 安装Python
RUN apt-get update && apt-get install -y python3 python3-pip && pip3 install Flask && pip3 install --upgrade spark_ai_python


ENV JAVA_OPTS=""
ENV APP_HOME /app
ENV APP_JAR tomato_websocket-1.0.0.jar
RUN mkdir $APP_HOME
WORKDIR $APP_HOME
ADD target/$APP_JAR $APP_HOME
ADD /src/main/resources/application-prod.yml $APP_HOME/application-dev.yml
ADD /src/main/resources/api_XF.py $APP_HOME/tomato_websocket/src/main/resources/api_XF.py
ADD /src/main/resources/bank.p $APP_HOME/tomato_websocket/src/main/resources/bank.p


ENTRYPOINT ["sh", "-c"]
CMD ["java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar $APP_HOME/$APP_JAR"]