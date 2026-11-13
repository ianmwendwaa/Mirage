# Created on 13/11/2026.

# This script helps log the developmental phase of Mirage, starting today.

from datetime import datetime
from io import TextIOWrapper
import textwrap

report_txt_path = "C:/Mirage/Mirage/pyscripts/dev_status_report.txt"

def report_file_empty():
    with open(report_txt_path, 'r') as report_file:
        report_file_content: list[str] = report_file.readlines()
    
        if len(report_file_content) == 0:
            return True 
    
        return False
    
def write_heading(report_file: TextIOWrapper):
    report_file.write("-" * 100)
    report_file.write("\n")
    report_file.write("\t\t\t\t\t\t\t\tMIRAGE DEVELOPMENT REPORT")
    report_file.write("\n")
    report_file.write("-"* 100)
    report_file.write("\n")

# @Todo: Make this a lambda
def get_dash_count(doc_label: str)->int:
    max_len = 100 # Maximum acceptable sentence length
    return (max_len - len(doc_label))//2
def write_into_report_file(): 
    log_date = datetime.now()
    log_message = input("Enter log message for today's build: ")
    chunked_str = textwrap.fill(log_message, width=100)
    
    start_log_label = f"[START_OF_LOG {datetime.strftime(log_date, "%d/%m/%Y, %H:%M")}]"
    footer = f"[END_OF_LOG {datetime.strftime(log_date, "%d/%m/%Y, %H:%M")}]"
    secondary_heading_dash_count = get_dash_count(doc_label=start_log_label)
    footer_dash_count = get_dash_count(doc_label=footer)
    if not log_message:
        print("No log message was provided. Terminating the app.")
    
    try:
        with open(report_txt_path, 'a') as report_file:
            if report_file_empty():
                write_heading(report_file)
                    
            report_file.write("\n")
            report_file.write("-"* secondary_heading_dash_count)
            report_file.write(start_log_label)
            report_file.write("-"* secondary_heading_dash_count + "\n")
                
            report_file.write(f"[LOG_DATE]: {datetime.strftime(log_date, "%d/%m/%Y, %H:%M")}" + '\n')
            report_file.write("[LOG_MESSAGE]\n")
            report_file.write(chunked_str + "\n")
            report_file.write("-"*footer_dash_count)
            report_file.write(footer)
            report_file.write("-"*footer_dash_count + "\n\n")
        print("Log data recorded successfully!")    
    except Exception as e:
        print(f"Error writing into report file. Reason: {e}")
if __name__ == '__main__':
    write_into_report_file()
