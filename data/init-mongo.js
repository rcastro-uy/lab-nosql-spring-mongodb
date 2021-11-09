print(' ######################### Script inicial #########################');
db.createUser(
  {
    user: 'api_user',
    pwd: 'NdEep0XLpMNKUmgQVa81oDCx7mrSRodh0Z79qdX3',
    roles: [{ role: 'readWrite', db: 'example' }],
  },
);
db.createCollection('usuario');
print('######################### Fin #########################');