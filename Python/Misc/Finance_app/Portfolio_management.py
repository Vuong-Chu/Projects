from dash import Dash, html, dcc, dash_table, Input, Output, State
import pandas as pd
import numpy as np
from datetime import date, datetime, timedelta
import plotly.express as px
import dash_bootstrap_components as dbc
import sys
# adding Folder (lib) to the system path
sys.path.insert(0, "/media/vuong/DATA/Desktop/Python Practice/Finance_app/lib")
from data_read import read_info

dbc_css = "https://cdn.jsdelivr.net/gh/AnnMarieW/dash-bootstrap-templates/dbc.min.css"


app = Dash(__name__, external_stylesheets=[dbc.themes.CYBORG, dbc_css],
           meta_tags=[{"name": "viewport",
                        "content": "width=device-width, initial-scale=1.0"}])

# Data
###############################################################################
freq = {
    "D": "Daily",
    "W": "Weekly",
    "M": "Monthly"
}
initial_active_cell = {"row": 0, "column": 0, "column_id": "Symbol", "row_id": 0}

df = read_info()

# Layout
###############################################################################
app.layout = dbc.Container(
    dbc.Stack(
        [   dbc.Row([
                html.H1("Select Stocks",
                        className="text-center mb-4")
            ]),
            dbc.Row([
                dbc.Col([
                    dbc.Label("Industry:",
                            className="fw-bold",
                            style={"textDecoration": "underline", "fontSize": 15}
                            ),
                    dcc.Dropdown(options = [], multi=True, id="industry", style={"height": "30px"})
                ], width={"size":6}   
                ),
                dbc.Col([
                     dbc.Label("Time Period:",
                            className="fw-bold",
                            style={"textDecoration": "underline", "fontSize": 15}
                            ),
                    dcc.Dropdown(options = freq, value="Daily", id="freq", style={"height": "30px"})
                ], width={"size":3}   
                ),
                dbc.Col([
                    dbc.Label("Frequency:",
                            className="fw-bold",
                            style={"textDecoration": "underline", "fontSize": 15}
                            ),
                    dcc.DatePickerRange(
                            id="my-date-picker-range",
                            min_date_allowed=date(2010, 1, 1),
                            max_date_allowed=date.today(),
                            start_date=date.today()-timedelta(days=100),
                            end_date=date.today()
                        )
                ], width={"size":3} 
                )
            ], align="start", justify="start"),
            dbc.Row([
                dbc.Col([
                    dbc.Label("Symbol/Company:",
                            className="fw-bold",
                            style={"textDecoration": "underline", "fontSize": 15}),
                    dcc.Dropdown(options = [], multi=True, id="symbol", style={"height": "30px", 'verticalAlign': 'bottom'})
                ], width={"size":12}, align="start")
            ], justify="start"),
            dbc.Row([
                 dbc.Button(
                            id="my-button",
                            children="Submit",
                            n_clicks=0,
                            color = "primary",
                            style={"height": "40px", 
                                   "width": "100px",
                                   'margin': "10px 10px", 
                                   "background-color":"#305D91"}
                        )
            ], justify="center"),
            dbc.Row([
                dbc.Col([
                    dash_table.DataTable(
                        id="company_info",
                        columns=[{"name": c, "id": c} for c in df.columns[:3]],
                        data=df.to_dict("records"),
                        page_size=15,
                        sort_action="native",
                        #active_cell=initial_active_cell,
                        style_header={"backgroundColor":"#305D91","padding":"10px","color":"#FFFFFF"},
                        style_cell={"font-size": 15,
                                    "overflow": "hidden",
                                    "textOverflow": "ellipsis"},
                        style_cell_conditional=[
                            {"if": {"column_id": "Symbol"},
                            "width": "100px",
                            "maxWidth": "100px",
                            "minWidth": "100px",
                            },
                            {"if": {"column_id": "Names"},
                            "width": "250px",
                            "maxWidth": "250px",
                            "minWidth": "250px",},
                            {"if": {"column_id": "Industry"},
                            "width": "200px",
                            "maxWidth": "200px",
                            "minWidth": "200px",},
                        ],
                        style_table={"height": "510px", "overflowY": "auto"}
                )
            ], width={"size":6}),
                dbc.Col([dbc.RadioItems(options={
                        "Industry": "Industry",
                        "Sub-industry": "Sub-industry",
                        "State": "Location"
                    },
                    value="Industry", inline=True),
                    dcc.Graph(figure={}, id="pie_chart", style={"width": "100%", "height": "521px"})
            ], width={"size":6})
            ], className="dbc")
        ], gap=3
    )
)

# Graph
###############################################################################

if __name__ == "__main__":
    app.run_server(debug=False, port=8080)