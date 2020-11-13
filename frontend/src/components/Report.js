import React, {useEffect, useState} from "react";
import {Card} from "react-bootstrap";
import {Settings} from "@material-ui/icons";
import '../css/CustomStyle.css'
import StatsTable from "./StatsTable";
import ReportPopup from "./ReportPopup";
import {CSVLink} from "react-csv";
import {retrieveReportItem, updateReport} from "../api";
import IconButton from "@material-ui/core/IconButton";
import {headers, url, port} from "../helper/Helper";


const moment = require("moment");

const Report = ({match}) => {

    const [data, setData] = useState()

    const [report, setReport] = useState({
        "id": "",
        "title": "",
        "runDate": "",
        "createdDate": "",
        "category": {
            "id": "",
            "title": ""
        },
        "numberOfUsers": '',
        "duration": ''
        , isFetching: false
    });

    const [show, setShow] = useState(false);

    const handleHide = () => {
        setShow(false)
    }

    const onHandleUpdate = (inputValues) => {
        setShow(false)
        console.log('entered handle update')

        let updatedValues = {
            "id": report.id,
            "title": inputValues.reportTitle,
            "runDate": report.runDate,
            "createdDate": report.createdDate,
            "category": {
                "id": inputValues.categoryId,
                "title": inputValues.categoryTitle
            },
            "numberOfUsers": report.numberOfUsers,
            "duration": report.duration,
            isFetching: false
        }

        const axiosParams = {
            url: url,
            port: port,
            id: match.params.id,
            payload: updatedValues
        }

        updateReport(axiosParams)

            .then((response) => {

                    setReport(prevState => {
                        return {...prevState, ...updatedValues};
                    });

                    console.log(response)
                }
            ).catch(function (error) {
            if (error.response) {
                console.log(error.response.data);
                console.log(error.response.status);
                console.log(error.response.headers);
            }
        });
    }

    const handleShow = () => {

        setShow(true);
    }

    useEffect(() => {

        const axiosParams = {
            url: url,
            port: port,
            id: match.params.id
        }

        retrieveReportItem(axiosParams)

            .then(({data}) => {

                    setReport({...data, isFetching: true})
                }
            )
    }, [report.id])

    function getDateFormatted(dateTimeStamp) {

        const date = moment(dateTimeStamp).format('DD-MMM-YYYY HH:mm:ss');

        return <> {date}</>;
    }

    function getGlobalStats(data) {

        let index
        data.stats.forEach((stats, i) => {
            if (stats.name === 'Global Information') {
                index = i
            }
        })

        return (
            <div>
                {data.stats !== undefined && index !== undefined &&
                <div>
                    <div> Total Requests: {data.stats[index].numberOfRequests.total}</div>
                    <div>Total Failed Requests: {data.stats[index].numberOfRequests.ko}</div>
                    <div>Number of users: {report.numberOfUsers}</div>
                    <div>Duration: {report.duration} seconds</div>
                </div>
                }
            </div>
        )
    }

    const onRetrieveInfo = (data) => {
        setData(data)
    }

    return <div style={{margin: '10px 10px'}}>
        <div style={{display: 'flex', marginBottom: '10px'}}>
            <Card style={{width: '18rem'}}>
                {report.id !== '' &&
                <Card.Body>
                    <Card.Title>Report Details
                        <IconButton className="IconButton" onClick={() => handleShow()}>
                            <Settings/>
                        </IconButton>
                        <ReportPopup
                            show={show}
                            onHide={handleHide}
                            onHandleUpdate={onHandleUpdate}
                            report={report}/>
                    </Card.Title>
                    <Card.Text>
                        Report name: {report.title}
                    </Card.Text>
                    <Card.Text>
                        Time Run: {getDateFormatted(report.runDate)}
                    </Card.Text>
                    <Card.Text>
                        Time Imported: {getDateFormatted(report.createdDate)}
                    </Card.Text>
                    <Card.Text>
                        Category: {report.category.title}
                    </Card.Text>
                </Card.Body>
                }
            </Card>
            <Card style={{width: '18rem', marginLeft: '10px', alignSelf: 'baseline'}}>
                {report.id !== '' &&
                <Card.Body>
                    <Card.Title>Information</Card.Title>
                    {data !== undefined &&
                    <Card.Text>
                        {getGlobalStats(data)}
                    </Card.Text>
                    }
                </Card.Body>
                }
            </Card>
            <Card style={{width: '18rem', marginLeft: '10px', alignSelf: 'baseline'}}>
                {report.id !== '' &&
                <Card.Body>
                    <Card.Title>Actions</Card.Title>
                    {data !== undefined &&
                    <Card.Text>
                        <CSVLink
                            style={{textDecoration: 'none'}}
                            className="button"
                            data={data.stats} headers={headers}>
                            Download CSV
                        </CSVLink>
                    </Card.Text>
                    }
                </Card.Body>
                }
            </Card>
        </div>
        <StatsTable match={match} onRetrieveInfo={onRetrieveInfo}/>
    </div>;
};

export default Report;