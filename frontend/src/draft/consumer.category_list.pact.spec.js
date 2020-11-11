const {Pact} = require('@pact-foundation/pact');
const {CategoryApiClient} = require('../api/api_pact');
const {Category} = require('../model/Category');
const {like, regex, somethingLike} = require('@pact-foundation/pact/dsl/matchers');
const chai = require("chai")
const expect = chai.expect

const mockProvider = new Pact({
    consumer: 'FRONTEND',
    provider: 'BACKEND',
    cors: true
});

describe('Category list API test', () => {
    beforeEach(() => mockProvider.setup());
    afterEach(() => mockProvider.verify());
    afterEach(() => mockProvider.finalize());

    it('get category list', async () => {
        const expectedCategory = {

            categories: [
                {
                    id: somethingLike("cdb02322-a8a6-4acf-9644-ddf8b24af9e6"),
                    title: somethingLike('My another category')
                },
            ],
        }

        await mockProvider.addInteraction({
            state: 'a category list',
            uponReceiving: 'a request to get a list of categories',
            withRequest: {
                method: 'GET',
                path: '/category'
            },
            willRespondWith: {
                status: 200,
                headers: {
                    'Content-Type': regex({generate: 'application/json', matcher: '^application\/json.*'}),
                },
                body: like(expectedCategory),
            },
        });

        const api = new CategoryApiClient(mockProvider.mockService.baseUrl);
        const categoryList = await api.getCategoryList();

        expect(categoryList.status).to.deep.equal(200);
    });


});