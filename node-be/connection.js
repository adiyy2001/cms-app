const {createPool} = require('mysql')

const pool = createPool({
    host: 'localhost',
    user: 'root',
    password: 'lG694&s0pkPD',
    connectionLimit: 50
})

module.exports = pool
