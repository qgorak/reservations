if (!date) return null
const [month, day, year] = date.split('/')
`${year}-${month.padStart(2, '0')}-${day.padStart(2, '0')}`