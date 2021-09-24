import {Matchers} from '@pact-foundation/pact';

const {updateStatsEndpoint} = require("../../api");
const {url, port} = require("../helper");
import providerBuilder, {PROVIDER} from '../pact/helpers/pactSetup';

const {somethingLike} = Matchers;

const GET_EXPECTED_BODY = {
    name: somethingLike('InteractEd Test Eval School 1-91002661')
};

const provider = providerBuilder(PROVIDER);

beforeAll(() => {
    return provider.setup().then((opts) => {
        process.env.API_PORT = opts.port;
    });
});

afterAll(() => provider.finalize());

afterEach(() => provider.verify());

describe('GET School', () => {
    beforeEach((done) => {
        const interaction = {
            state: 'i have a school',
            uponReceiving: 'a request for a school',
            withRequest: {
                method: 'GET',
                path: '/ids/v1/schools/0d2bf746-ae98-4bb4-a807-8c2db6d2852d',
            },
            willRespondWith: {
                status: 200,
                body: GET_EXPECTED_BODY,
            },
        };
        provider.addInteraction(interaction).then(() => done());
    });

    test('generate contract', async () => {
      const axiosParams = {
        url: url,
        port: port,
        id: "0531c13b-a5ac-4314-bac6-fdfd89c9e0c2",
        payload: GET_EXPECTED_BODY
      }

      const response = updateStatsEndpoint(axiosParams)
      expect(response.status).toEqual(200);
    });

});
