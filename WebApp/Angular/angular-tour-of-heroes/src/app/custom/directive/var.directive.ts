import { Directive, ViewContainerRef, TemplateRef, Input } from '@angular/core';

@Directive({
  selector: '[appVar]'
})
export class VarDirective {

  context: any = {};

  constructor(private viewContainerRef: ViewContainerRef, private templateRef: TemplateRef<any>) { }

  @Input()
  set appVar(context: any) {
    this.context.$implicit = this.context.appVar = context;
    this.updateView();
  }

  updateView() {
    this.viewContainerRef.createEmbeddedView(this.templateRef, this.context);
  }

}
