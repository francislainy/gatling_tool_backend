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

function App() {

    const [showConfirmationModal, setShowConfirmationModal] = useState(false)

    const handleDeletePopUp = () => {

        setShowConfirmationModal(true)
    }

    const onConfirmDelete = (id) => {

        const axiosParams = {
            url: url,
            port: port,
            id: id
        }

        deleteReport(axiosParams).then(() => {
            setShowConfirmationModal(false)
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
                    onConfirmDelete={onConfirmDelete}
                    handleDeletePopUp={handleDeletePopUp}
                    showConfirmationModal={showConfirmationModal}
                    onHide={onHide}
                    {...props}/>}/>
                <Route path="/report" exact component={Report}/>
            </Switch>
        </Router>
    );
}

export default App;