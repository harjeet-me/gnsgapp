import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GnsgappSharedModule } from 'app/shared/shared.module';
import { SevadarComponent } from './sevadar.component';
import { SevadarDetailComponent } from './sevadar-detail.component';
import { SevadarUpdateComponent } from './sevadar-update.component';
import { SevadarDeleteDialogComponent } from './sevadar-delete-dialog.component';
import { sevadarRoute } from './sevadar.route';

@NgModule({
  imports: [GnsgappSharedModule, RouterModule.forChild(sevadarRoute)],
  declarations: [SevadarComponent, SevadarDetailComponent, SevadarUpdateComponent, SevadarDeleteDialogComponent],
  entryComponents: [SevadarDeleteDialogComponent],
})
export class GnsgappSevadarModule {}
