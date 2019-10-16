const csv = require("csvtojson");

const csvFilePath = process.argv[2];
const manufacturer = process.argv[3];

if (!csvFilePath) {
  console.log("No CSV file given");
  return;
}

if (!manufacturer) {
  console.log("No manufacturer name given");
  return;
}

csv()
  .fromFile(csvFilePath)
  .then(row => {
    const entries = row.map(row => {
      const href = row["name-href"];
      const id = href.match(new RegExp("/(\\d+)/$"))[1];
      return {
        avgRating: 0,
        avgPrice: 0,
        category: row.category,
        manufacturer: manufacturer,
        name: row.name,
        numRatings: 0,
        numPrices: 0,
        photo: `https://res.cloudinary.com/ratebeer/image/upload/d_beer_img_default.png/beer_${id}`
      };
    });
    console.log(JSON.stringify({ beers: entries }));
  });
