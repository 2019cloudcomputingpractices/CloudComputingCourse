from flask import render_template
from flask_login import current_user
from . import main_blueprint


@main_blueprint.route('/')
def index():
    print(current_user)
    return render_template('index.html', current_user=current_user)


@main_blueprint.app_errorhandler(404)
def page_not_found(e):
    print('404 handled')
    return render_template('404.html'), 404


@main_blueprint.app_errorhandler(500)
def internal_server_error(e):
    return render_template('500.html'), 500
