FROM python:3.5

# Create app directory
WORKDIR /app

# Install app dependencies
COPY src/requirments.txt ./

RUN pip install -r ./requirments.txt

# Bundle app source
COPY src /app

EXPOSE 8080
CMD [ "python", "server.py" ]