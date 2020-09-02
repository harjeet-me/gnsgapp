import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GnsgappSharedModule } from 'app/shared/shared.module';
import { ASProgramComponent } from './as-program.component';
import { ASProgramDetailComponent } from './as-program-detail.component';
import { ASProgramUpdateComponent } from './as-program-update.component';
import { ASProgramDeleteDialogComponent } from './as-program-delete-dialog.component';
import { aSProgramRoute } from './as-program.route';

@NgModule({
  imports: [GnsgappSharedModule, RouterModule.forChild(aSProgramRoute)],
  declarations: [ASProgramComponent, ASProgramDetailComponent, ASProgramUpdateComponent, ASProgramDeleteDialogComponent],
  entryComponents: [ASProgramDeleteDialogComponent],
})
export class GnsgappASProgramModule {}
