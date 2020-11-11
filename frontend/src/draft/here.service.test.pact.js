// hero.service.test.pact.js
const HeroService = require('./hero.service');
import * as Pact from '@pact-foundation/pact';
import Hero from './Hero';

describe('HeroService API', () => {

    const heroService = new HeroService('http://localhost', global.port);

    describe('createHero()', () => {

        beforeEach((done) => {
            const contentTypeJsonMatcher = Pact.Matchers.term({
                matcher: "application\\/json; *charset=utf-8",
                generate: "application/json; charset=utf-8"
            });

            global.provider.addInteraction({
                state: 'provider allows hero creation',
                uponReceiving: 'a POST request to create a hero',
                withRequest: {
                    method: 'POST',
                    path: '/heroes',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': contentTypeJsonMatcher
                    },
                    body: new Hero(null, 'Superman', 'flying', 'DC')
                },
                willRespondWith: {
                    status: 201,
                    headers: {
                        'Content-Type': contentTypeJsonMatcher
                    },
                    body: Pact.Matchers.somethingLike(
                        new Hero(42, 'Superman', 'flying', 'DC'))
                }
            }).then(() => done());
        });

        it('sends a request according to contract', (done) => {
            heroService.createHero(new Hero('Superman', 'flying', 'DC'))
                .then(response => {
                    const hero = response.data;
                    expect(hero.id).toEqual(42);
                })
                .then(() => {
                    global.provider.verify()
                        .then(() => done(), error => {
                            done.fail(error)
                        })
                });
        });

    });

});