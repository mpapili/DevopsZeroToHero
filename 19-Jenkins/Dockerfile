FROM python

COPY . /app
WORKDIR /app

RUN python3 -m pip install black flake8

CMD ["python3", "my_script.py"]
