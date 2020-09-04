import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GnsgappSharedModule } from 'app/shared/shared.module';
import { ExpenseReportComponent } from './expense-report.component';
import { ExpenseReportDetailComponent } from './expense-report-detail.component';
import { ExpenseReportUpdateComponent } from './expense-report-update.component';
import { ExpenseReportDeleteDialogComponent } from './expense-report-delete-dialog.component';
import { expenseReportRoute } from './expense-report.route';

@NgModule({
  imports: [GnsgappSharedModule, RouterModule.forChild(expenseReportRoute)],
  declarations: [ExpenseReportComponent, ExpenseReportDetailComponent, ExpenseReportUpdateComponent, ExpenseReportDeleteDialogComponent],
  entryComponents: [ExpenseReportDeleteDialogComponent],
})
export class GnsgappExpenseReportModule {}
