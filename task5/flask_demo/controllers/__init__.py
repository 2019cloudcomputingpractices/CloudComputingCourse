from flask import Blueprint

main_blueprint = Blueprint(
    'main',
    __name__,
    template_folder='../templates',
    static_folder='../static',
)

auth_blueprint = Blueprint(
    'auth',
    __name__,
    template_folder='../templates',
    static_folder='../static',
)


from . import auth
from . import main