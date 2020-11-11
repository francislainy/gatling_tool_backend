import { pactWith } from 'jest-pact';
import { Matchers } from '@pact-foundation/pact';

const {CategoryApiClient} = require("../api/api_pact");

const { string, integer, somethingLike, uuid } = Matchers;

const startDate = new Date(Date.now());
startDate.setUTCHours(23);
startDate.setUTCMinutes(59);
startDate.setUTCSeconds(59);

const startDateStr = startDate.toISOString();

const dummyMeetingSchema = {
    title: 'Sample title creating meeting',
    startDateTime: startDateStr,
    duration: 120,
    provider: 'zoom',
    attributes: {
        meetingId: '5678',
        joinUrl: 'zoom.com',
    },
    participants: [
        {
            hmhUserRefId: '23aec974-0838-40d6-8cfd-0b1a2f5c6b39',
        },
    ],
};

pactWith(
    {
        consumer: 'Ed UI',
        provider: 'Virtual Classroom Service',
        cors: true,
        pactfileWriteMode: 'merge',
    },

    provider => {
        describe('CreateMeetingApi creating a new vc meeting', () => {
            const expectedResponse = {
                id: integer(4),
                title: string('VC title'),
                startDateTime: somethingLike('2020-10-01T10:00:00+0000'),
                duration: integer(30),
                provider: 'zoom',
                attributes: {
                    meetingId: integer('5678'),
                    joinUrl: string('zoom.com'),
                },
                participants: [
                    {
                        id: somethingLike(1234),
                        hmhUserRefId: uuid('c1a46705-9a56-47e8-8770-221b60767617'),
                    },
                ],
            };

            beforeEach(async () => {
                await provider.addInteraction({
                    state: 'A request to create a virtual classroom meeting',
                    uponReceiving: 'A request to create a virtual classroom meeting',
                    withRequest: {
                        method: 'GET',
                        path: '/category/cdb02322-a8a6-4acf-9644-ddf8b24af9e6',
                        body: dummyMeetingSchema,
                        headers: {
                            accept: 'application/json, text/plain, */*',
                        },
                    },
                    willRespondWith: {
                        status: 201,
                        headers: {
                            'content-type': 'application/json;charset=UTF-8',
                        },
                        body: expectedResponse,
                    },
                });
            });

            it('returns correct response', async () => {
                const api = new CategoryApiClient(provider.mockService.baseUrl);

                const result = await api.getCategory("cdb02322-a8a6-4acf-9644-ddf8b24af9e6", {
                    baseURL: `${provider.mockService.baseUrl}`,
                });
                expect(result.status).toStrictEqual(200);
            });
        });
    },
);

