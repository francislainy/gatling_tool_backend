import React, {useState} from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import './simple-sidebar.css'
import Report from "./pages/Report";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Home from "./pages/Home";
import {port, url} from "./helper/Helper";
import {deleteReport} from "./api";
import Settings from "./pages/Settings";

function App() {

    const [showConfirmationModal, setShowConfirmationModal] = useState(false)
    const [showAlert, setShowAlert] = useState(false)
    const delay = ms => new Promise(res => setTimeout(res, ms));

    const handleDeletePopUp = () => {

        setShowConfirmationModal(true)
    }

    const onConfirmDelete = (props) => {

        const axiosParams = {
            url: url,
            port: port,
            id: props.match.params.id
        }

        deleteReport(axiosParams).then(async () => {
            setShowConfirmationModal(false)

            setShowAlert(true)

            await delay(2000)
            props.history.goBack()

            setShowAlert(false)

        })
    }

    const onHide = (section) => {

        switch (section) {
            case 'delete':
                setShowConfirmationModal(false)
                break
        }
    }

    return (
        <Router>
            <Switch>
                <Route path="/" exact component={Home}/>
                <Route path="/report/:id" exact render={props => <Report
                    onConfirmDelete={() => onConfirmDelete(props)}
                    showAlert={showAlert}
                    handleDeletePopUp={handleDeletePopUp}
                    showConfirmationModal={showConfirmationModal}
                    onHide={onHide}
                    {...props}/>}/>
                <Route path="/report" exact component={Report}/>
                <Route path="/settings" exact component={Settings}/>
            </Switch>
        </Router>
    );
}

export default App;