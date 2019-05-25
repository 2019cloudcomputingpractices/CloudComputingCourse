from flask import Flask
from flask import render_template
from flask import request
import sqlite3

conn = sqlite3.connect('database.db')
conn.execute("drop table if exists user")
conn.execute("CREATE TABLE user ( \
  id INTEGER PRIMARY KEY AUTOINCREMENT,                     \
  username TEXT UNIQUE NOT NULL,                            \
  password TEXT NOT NULL                                    \
);")
conn.close()

app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'Hello, World!'

@app.route('/register', methods=['GET', 'POST'])
def register():
    if request.method == 'GET':
        return render_template('demo-register.html')
    else:
        print("user post received")
        username = request.form['username']
        password = request.form['password']
        with sqlite3.connect("database.db") as con:
            cur = con.cursor()
            cur.execute("insert into user(username,password) values(?,?)", (username, password))
        con.commit()
        return "register succeed"



@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'GET':
        return render_template('demo-login.html')
    else:
        username = request.form['username']
        password = request.form['password']
        con = sqlite3.connect("database.db")
        con.row_factory = sqlite3.Row
        cur = con.cursor()
        cur.execute("select * from user where username = ?", (username,))
        rows = cur.fetchall()
        if len(rows) == 0 or rows[0]["password"] != password:
            return "user or password doesn't match"
        return "login succeed"
        

