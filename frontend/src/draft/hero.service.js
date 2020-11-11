// hero.service.js
const axios = require('axios');
import adapter from 'axios/lib/adapters/http';

class HeroService {
    createHero(hero) {
        this._validateHeroForCreation(hero);
        return axios.request({
            // ...
        }).then((response) => {
            const hero = response.data;
            return new Promise((resolve, reject) => {
                try {
                    this._validateIncomingHero(hero);
                    resolve(hero);
                } catch (error) {
                    reject(error);
                }
            });
        });
    };
}

module.exports = HeroService