from flask import Flask, render_template, url_for, flash, redirect
from flask_wtf import FlaskForm
from wtforms import StringField, PasswordField, BooleanField, SubmitField
from wtforms.validators import DataRequired, equal_to


class LoginForm(FlaskForm):
    username = StringField('username', validators=[DataRequired()])
    password = PasswordField('password', validators=[DataRequired()])
    remember_me = BooleanField('Remember me')
    submit = SubmitField('Sign In')


class SignUpForm(FlaskForm):
    username = StringField('username', validators=[DataRequired()])
    password = PasswordField('password', validators=[DataRequired()])
    passwordConfirm = PasswordField('passwordConfirm', validators=[equal_to('password', '密码不一致')])
    submit = SubmitField('Sign Up')


app = Flask(__name__)

app.config['SECRET_KEY'] = 'YOUR_SECRET_KEY'  # for example, '3ef6ffg4'

users = {'default': 'default01'}
currentUse = 'Null'


@app.route('/home', methods=['get'])
@app.route('/', methods=['get'])
def home():
    return render_template('home.html',
                           title='home',
                           user=currentUse)


@app.route('/login', methods=['get', 'post'])
def login():
    global currentUse
    form = LoginForm()
    if form.validate_on_submit():  # post && everything is all right it will return True
        if form.username.data in users.keys():
            if form.password.data == users[form.username.data]:
                flash('Login successful for user={}, remember_me={}'.format(form.username.data, form.remember_me.data))
                currentUse = form.username.data
                return redirect(url_for('home'))
    else:
        flash('Username or password error')
    return render_template('login.html', title='Sign In', form=form)


@app.route('/signup', methods=['get', 'post'])
def signup():
    form = SignUpForm()
    if form.username.data in users.keys():  # username has been used
        flash('The username has been used')
    elif form.validate_on_submit():
        flash('Sign Up successfully')
        users[form.username.data] = form.password.data
        return redirect(url_for('login'))
    return render_template('signup.html', title='Sign Up', form=form)


if __name__ == '__main__':
    app.run()
