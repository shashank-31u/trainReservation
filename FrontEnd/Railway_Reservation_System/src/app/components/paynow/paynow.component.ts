import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
@Component({
  selector: 'app-paynow',
  template: '<form [innerHTML]="getScript()"></form>',
  styleUrl:'./paynow.component.css'
})
export class PaynowComponent {
  constructor(private sanitizer: DomSanitizer){}
  getScript() {
    const scriptUrl = 'https://checkout.razorpay.com/v1/payment-button.js';
    const scriptContent = `<script src="${scriptUrl}" data-payment_button_id="pl_NGH3fTSB8qHUjI" async></script>`;
    
    // Use DomSanitizer to mark the script content as safe
    return this.sanitizer.bypassSecurityTrustHtml(scriptContent);
  }

}
