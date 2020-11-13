import React from "react";

class Navbar extends React.Component {
    render() {
        return <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
            <div><a href="#" className="navbar-brand">Gatling Reporting Tool</a></div>
            <button className="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"/>
            </button>
            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav mr-auto">
                    <li className="nav-item active">
                        <a className="nav-link" href="#">Dashboard</a>
                    </li>
                </ul>
            </div>
        </nav>;
    }
}

export default Navbar;