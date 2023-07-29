import pandas as pd
import numpy as np
import yfinance as yf

# Get the current S&P500 components, and get a tickers list
def read_info():
    sp_assets = pd.read_html("https://en.wikipedia.org/wiki/List_of_S%26P_500_companies")[0]

    symbol = sp_assets["Symbol"].str.replace(".","-").tolist()
    name = sp_assets["Security"].tolist()
    sector = sp_assets["GICS Sector"].tolist()
    sub_sector = sp_assets["GICS Sub-Industry"].tolist()
    location = sp_assets["Headquarters Location"].replace(" ","").tolist()
    date_add = sp_assets["Date added"].tolist()
    cik = sp_assets["CIK"].tolist()
    founded = sp_assets["Founded"].tolist()

    col_label = ["Symbol", "Names", "Industry", "Sub-industry", "Location", "Date added", "CIK", "Founded"]
    df = np.array([symbol, name, sector, sub_sector, location, date_add, cik, founded]).T
    df = pd.DataFrame(df, columns = col_label)
    df[["County", "State"]] = df.Location.str.rsplit(",", n=1, expand = True)
    df.drop(["Location", "Date added", "CIK", "Founded"], axis = 1, inplace = True)
    df.sort_values(by=['Symbol'], inplace=True)
    #df.set_index(["Symbol"], inplace=True)
    return df