// (1) Import the pact library and matching methods
const {Pact} = require('@pact-foundation/pact');
const {ProductApiClient} = require('../api/api_pact');
const {Product} = require('../model/Product');
const {like, eachLike, regex} = require('@pact-foundation/pact/dsl/matchers');
const chai = require("chai")
const {CategoryApiClient} = require("../api/api_pact");
const expect = chai.expect

// (2) Configure our Pact library
const provider = new Pact({
    consumer: 'katacoda-consumer',
    provider: 'katacoda-provider',
    cors: true // needed for katacoda environment
});

describe('Category Service', () => {
    describe('When a request to get a category is made', () => {
        beforeAll(() =>
            provider.setup().then(() => {
                provider.addInteraction({
                    uponReceiving: 'a request to list all movies',
                    withRequest: {
                        method: 'GET',
                        path: '/category/cdb02322-a8a6-4acf-9644-ddf8b24af9e6',
                    },
                    willRespondWith: {
                        status: 200,
                        body: eachLike(
                            {
                                id: "cdb02322-a8a6-4acf-9644-ddf8b24af9e6",
                                title: like('Another category'),
                            },
                            {min: 1}
                        ),
                    },
                });
            })
        );

        test('should return the correct data', async () => {
            const api = new CategoryApiClient(provider.mockService.baseUrl);
            const response = await api.getCategory("cdb02322-a8a6-4acf-9644-ddf8b24af9e6");
            expect(response.status).equal(200);
        });

        afterEach(() => provider.verify());
        afterAll(() => provider.finalize());
    });
});