print('############################# CARGANDO USUARIO PARA API EN MONDO DB ####################################');
db.createUser(
    {
        user: 'api_user',
        pwd: 'NdEep0XLpMNKUmgQVa81oDCx7mrSRodh0Z79qdX3',
        roles: [{ role: 'readWrite', db: 'nosql-db' }],
    },
);
db.createCollection('usuario');
print('############################### FIN ##################################');