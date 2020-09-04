import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GnsgappSharedModule } from 'app/shared/shared.module';
import { PathReportComponent } from './path-report.component';
import { PathReportDetailComponent } from './path-report-detail.component';
import { PathReportUpdateComponent } from './path-report-update.component';
import { PathReportDeleteDialogComponent } from './path-report-delete-dialog.component';
import { pathReportRoute } from './path-report.route';

@NgModule({
  imports: [GnsgappSharedModule, RouterModule.forChild(pathReportRoute)],
  declarations: [PathReportComponent, PathReportDetailComponent, PathReportUpdateComponent, PathReportDeleteDialogComponent],
  entryComponents: [PathReportDeleteDialogComponent],
})
export class GnsgappPathReportModule {}
