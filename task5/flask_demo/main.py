from flask import Flask
from flask_bootstrap import Bootstrap
from flask_sqlalchemy import SQLAlchemy
from flask_login import LoginManager
from config import config


bootstrap = Bootstrap()
db = SQLAlchemy()
login_manager = LoginManager()
login_manager.login_view = 'auth.login'


def create_app(config_name):
    app = Flask(__name__)
    app.config.from_object(config[config_name])
    config[config_name].init_app(app)

    bootstrap.init_app(app)
    db.init_app(app)
    login_manager.init_app(app)

    from controllers import main_blueprint
    app.register_blueprint(main_blueprint)
    from controllers import auth_blueprint
    app.register_blueprint(auth_blueprint)
    return app


app = create_app('development')


if __name__ == '__main__':
    app.run()