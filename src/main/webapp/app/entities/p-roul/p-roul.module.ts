import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GnsgappSharedModule } from 'app/shared/shared.module';
import { PRoulComponent } from './p-roul.component';
import { PRoulDetailComponent } from './p-roul-detail.component';
import { PRoulUpdateComponent } from './p-roul-update.component';
import { PRoulDeleteDialogComponent } from './p-roul-delete-dialog.component';
import { pRoulRoute } from './p-roul.route';

@NgModule({
  imports: [GnsgappSharedModule, RouterModule.forChild(pRoulRoute)],
  declarations: [PRoulComponent, PRoulDetailComponent, PRoulUpdateComponent, PRoulDeleteDialogComponent],
  entryComponents: [PRoulDeleteDialogComponent],
})
export class GnsgappPRoulModule {}
