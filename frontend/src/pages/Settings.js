import React, {useEffect, useState} from 'react';
import Button from "react-bootstrap/Button";
import Popup from "../components/Popup";
import ReportTable from "../components/ReportTable";
import api from "../api/api";
import {createReport, deleteReport, retrieveReports} from "../api";
import ConfirmationModal from "../components/ConfirmationModal";
import Sidebar from "../components/Sidebar";
import Navbar from "../components/Navbar";
import {url, port} from "../helper/Helper";
import Alert from "@material-ui/lab/Alert";
import CheckIcon from "@material-ui/icons/Check";

function Settings() {

    return (
        <div>
            Hello from settings
        </div>
    );
}

export default Settings