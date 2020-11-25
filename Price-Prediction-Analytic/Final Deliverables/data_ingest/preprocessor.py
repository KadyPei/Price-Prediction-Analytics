#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Apr  1 09:44:04 2020

@author: rickmaeda
"""
# Import packages
import sys
import pandas as pd
import numpy as np
from pandas.api.types import is_string_dtype

# main class
class DataAnalyser:
    def __init__(self, dataset_urls):
        self.dataset_urls = dataset_urls
        return
    def downloadFiles(self):
        print("Downloading {} files.".format(len(self.dataset_urls)))
        # Download excel files
        self.raw_data = [pd.read_excel(_,skiprows = 4) for _ in self.dataset_urls]
        print("{}/{} files downloaded.".format(len(self.raw_data), len(self.dataset_urls)))
    def initial_clean(self, each_df):
        # Transformations
        # remove the commas from the dataset
        each_df.dropna(subset=['ZIP CODE'], inplace = True)
        for col in each_df.columns:
            if is_string_dtype(each_df[col]):
                each_df[col] = each_df[col].str.replace(',', '')
            if col == "ZIP CODE":
                each_df[col] = each_df[col].astype('int32')
        return each_df
    def cleanDFs(self):
        self.cleaned_dfs = [self.initial_clean(_) for _ in self.raw_data]
        print("All dataframes cleaned.")
        return
    def concatDFs(self):
        self.main_df = pd.concat(self.cleaned_dfs).reset_index()
        print("Dataframes concatenated into one massive table.")
        return
    def exportData(self, filename):
        (self.main_df).to_csv(filename, header = True, index = False)
        print("Done. Dataframe exported as {}".format(filename))
        return
    def analyse(self, local_address):
        with open(local_address) as f:
            tempdata = [line.count(',') for line in f.readlines()]
        values, counts = np.unique(tempdata, return_counts = True)
        for val, count in zip(values, counts):
            print("{} entries have {} commas.".format(count, val))
        return

def main(argv):
    # Fixed variables
    dataset_urls = ["https://www1.nyc.gov/assets/finance/downloads/pdf/rolling_sales/rollingsales_manhattan.xls",
                    "https://www1.nyc.gov/assets/finance/downloads/pdf/rolling_sales/rollingsales_bronx.xls",
                    "https://www1.nyc.gov/assets/finance/downloads/pdf/rolling_sales/rollingsales_brooklyn.xls",
                    "https://www1.nyc.gov/assets/finance/downloads/pdf/rolling_sales/rollingsales_queens.xls",
                    "https://www1.nyc.gov/assets/finance/downloads/pdf/rolling_sales/rollingsales_statenisland.xls"
                    ]

    # call the class
    myProcessor = DataAnalyser(dataset_urls)
    # download the files
    myProcessor.downloadFiles()
    # clean all the tables
    myProcessor.cleanDFs()
    # concatenate all dfs
    myProcessor.concatDFs()
    # export the data
    myProcessor.exportData('rollingsales_inputdata.txt')
    # analyse - optional
    if len(argv) == 2 and argv[1] == '-':
        myProcessor.analyse('rollingsales_data_clean.txt')
    return 0

if __name__ == '__main__':
    sys.exit(main(sys.argv))

"""
Entries to keep in column 'BUILDING CLASS CATEGORY':
    09 COOPS - WALKUP APARTMENTS
    10 COOPS - ELEVATOR APARTMENTS
    12 CONDOS - WALKUP APARTMENTS
    13 CONDOS - ELEVATOR APARTMENTS
    01 ONE FAMILY DWELLINGS

Exceptions:
    09 COOPS - WALKUP APARTMENTS:
        331 WEST 71ST STREET, 2,3,4
        78-86 THAYER STREET
        106 SPRING STREET, 1
        165 EAST 66TH ST, RESI
# True if address not in the above exception (requires an exact match)
address_errors = "331 WEST 71ST STREET, 2,3,4|106 SPRING STREET, 1".split('|')
np.in1d(each_df['ADDRESS'], address_errors, invert = True)
"""
