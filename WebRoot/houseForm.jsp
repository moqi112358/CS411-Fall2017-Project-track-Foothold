<%@ page import="modules.*" %>


<div class="form-all">
    <ul class="form-section page-section">


        <li id="cid_19" class="form-input-wide" data-type="control_head">
            <div class="form-header-group ">
                <div class="header-text httal htvam">
                    <h2 id="header_19" class="form-header" data-component="header">
                        Property Upload
                    </h2>
                    <div id="subHeader_19" class="form-subHeader">
                        Please fill the form below about the property
                    </div>
                </div>
            </div>
        </li>

            <%if(hb==null){%>

        <li class="form-line jf-required" data-type="control_textbox" id="id_1">
            <label class="form-label form-label-top form-label-auto" id="label_1" for="input_1">
                The property name:<span class="form-required">*</span>
            </label>
            <div  class="form-input-wide jf-required">
                <input type="text"  name="name" data-type="input-textbox"
                       class="form-textbox validate[required]" size="48" value=""
                       data-component="textbox" required=""/>

            </div>
        </li>


        <li class="form-line jf-required" data-type="control_address" id="id_20">
            <label class="form-label form-label-top form-label-auto" > Address </label>
            <div  class="form-input-wide">
                <table summary="" class="form-address-table" cellpadding="0" cellspacing="0">
                    <tbody>
                    <tr>
                        <td colspan="2">
                          <span class="form-sub-label-container" style="vertical-align:top;">
                            <input type="text"  name="street"
                                   class="form-textbox form-address-line" value="" required="" data-component="address_line_1"/>
                            <label class="form-sub-label" for="input_20_addr_line1"
                                   style="min-height:13px;"> Street Address </label>
                          </span>
                        </td>
                    </tr>
                    <tr>
                        <td width="50%">
                              <span class="form-sub-label-container" style="vertical-align:top;">
                                <input type="text"  name="city"
                                       class="form-textbox form-address-city" size="21" value="" data-component="city"/>
                                <label class="form-sub-label" for="input_20_city" required="" style="min-height:13px;"> City </label>
                              </span>
                        </td>
                        <td>
                                  <span class="form-sub-label-container" style="vertical-align:top;">
                                            <input type="text"  name="state"
                                                   class="form-textbox form-address-state" size="22" value="" data-component="state"/>
                                            <label class="form-sub-label" for="input_20_state" required=""  style="min-height:13px;"> State / Province </label>
                                  </span>
                        </td>
                    </tr>
                    <tr>
                        <td width="50%">
                              <span class="form-sub-label-container" style="vertical-align:top;">
                                <input type="text"  name="zipcode"
                                       class="form-textbox form-address-postal" size="10" value="" required="" data-component="zip"/>
                                <label class="form-sub-label" for="input_20_postal"
                                       style="min-height:13px;"> Postal / Zip Code </label>
                              </span>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </li>


        <li class="form-line" data-type="control_textarea" >
            <label class="form-label form-label-top form-label-auto"  >
                Please describe the house:
            </label>
            <div class="form-input-wide">
                <textarea  class="form-textarea" name="description" cols="40" rows="6" data-component="textarea" value=""></textarea>
            </div>
        </li>

        <li class="form-line" data-type="control_textarea" >
            <label class="form-label form-label-top form-label-auto"  >
                Please describe the space around(optional):
            </label>
            <div class="form-input-wide">
                <textarea  class="form-textarea" name="space" cols="40" rows="6" data-component="textarea" value=""></textarea>
            </div>
        </li>

        <li class="form-line" data-type="control_textarea" >
            <label class="form-label form-label-top form-label-auto"  >
                Please put some notes here:
            </label>
            <div class="form-input-wide">
                <textarea  class="form-textarea" name="notes" cols="40" rows="6" data-component="textarea" value=""></textarea>
            </div>
        </li>

        <li class="form-line" data-type="control_textarea" >
            <label class="form-label form-label-top form-label-auto"  >
                Please describe the transit:
            </label>
            <div class="form-input-wide">
                <textarea  class="form-textarea" name="transit" cols="40" rows="6" data-component="textarea" value=""></textarea>
            </div>
        </li>


        <li class="form-line" data-type="control_textarea" >
            <label class="form-label form-label-top form-label-auto"  >
                Please describe the Access:
            </label>
            <div class="form-input-wide">
                <textarea  class="form-textarea" name="access" cols="40" rows="6" data-component="textarea" value=""></textarea>
            </div>
        </li>

        <li class="form-line" data-type="control_textarea" >
            <label class="form-label form-label-top form-label-auto"  >
                Please describe the interaction:
            </label>
            <div class="form-input-wide">
                <textarea  class="form-textarea" name="interaction" cols="40" rows="6" data-component="textarea" value=""></textarea>
            </div>
        </li>

        <li class="form-line" data-type="control_textarea" >
            <label class="form-label form-label-top form-label-auto"  >
                Please describe the house-rules:
            </label>
            <div class="form-input-wide">
                <textarea  class="form-textarea" name="house_rules" cols="40" rows="6" data-component="textarea" value=""></textarea>
            </div>
        </li>


        <li class="form-line jf-required" data-type="control_textbox" >
            <label class="form-label form-label-top form-label-auto" >
                The property type( apartment/house/ect ):
            </label>
            <div class="form-input-wide jf-required">
                <input type="text"  name="property_type" data-type="input-textbox" class="form-textbox" size="48" value="" data-component="textbox" value="" />
            </div>
        </li>


        <li class="form-line" data-type="control_radio" >
            <label class="form-label form-label-top form-label-auto" > room type </label>
            <div  class="form-input-wide">
                <div class="form-single-column" data-component="radio">
                    <span class="form-radio-item" style="clear:left;">
                      <span class="dragger-item">
                      </span>
                      <input type="radio" class="form-radio" name="room_type" value="0"/>
                      <label  > Private Room </label>
                    </span>
                    <span class="form-radio-item" style="clear:left;">
                      <span class="dragger-item">
                      </span>
                      <input type="radio" class="form-radio" name="room_type" value="1"/>
                      <label > Entire Home </label>
                    </span>
                    <span class="form-radio-item" style="clear:left;">
                      <span class="dragger-item">
                      </span>
                      <input type="radio" class="form-radio" name="room_type" value="2"/>
                      <label > Shared Room </label>
                    </span>
                </div>
            </div>
        </li>



        <li class="form-line jf-required" data-type="control_phone">

            <div  class="form-input jf-required">
                <div data-wrapper-react="true">

            <span class="form-sub-label-container" style="vertical-align:top;">
              <label class="form-sub-label"  style="min-height:13px;"> Bedroom Number: </label>
              <input type="number"  class="form-textbox" size="3" value="" required=""  name ="bedrooms" />
            </span>
                    <span class="form-sub-label-container" style="vertical-align:top;">
              <label class="form-sub-label"  style="min-height:13px;" name="bathrooms"> Bathroom Number: </label>
              <input type="number"  class="form-textbox" size="3" value="" required="" name="bathrooms" />
            </span>
                    <span class="form-sub-label-container" style="vertical-align:top;">
              <label class="form-sub-label"   style="min-height:13px;" > Bed Number:</label>
              <input type="number"  class="form-textbox" size="3" value=""  required="" name="beds" />
            </span>
                </div>
            </div>
        </li>

        <li class="form-line jf-required" data-type="control_phone" id="id_21">

            <div  class="form-input jf-required">
                <div data-wrapper-react="true">

            <span class="form-sub-label-container" style="vertical-align:top;">
              <label class="form-sub-label"  style="min-height:13px;"> People include: </label>
              <input type="number"  class="form-textbox" size="3" value=""  required="" name ="accommodates" />
            </span>
                    <span class="form-sub-label-container" style="vertical-align:top;">
              <label class="form-sub-label"  style="min-height:13px;" > Price: </label>
              <input type="number"  class="form-textbox" size="3" value="" required="" name="price"/>
            </span>
                    <span class="form-sub-label-container" style="vertical-align:top;">
              <label class="form-sub-label"   style="min-height:13px;" >Security deposites: </label>
              <input type="number"  class="form-textbox" size="3" value=""  name="security_deposites" />
            </span>
                </div>
            </div>
        </li>
        <li class="form-line jf-required" data-type="control_phone" id="id_21">
        <input type="button" value="get Price" onclick="ajaxSubmit()">
        </li>

            <%}else{%>



        <li class="form-line jf-required" data-type="control_textbox" id="id_1">
            <label class="form-label form-label-top form-label-auto"  for="input_1">
                The property name:<span class="form-required">*</span>
            </label>
            <div id="cid_1" class="form-input-wide jf-required">
                <input type="text" id="input_1" name="name" data-type="input-textbox"
                       class="form-textbox validate[required]" size="48" value="<%=hb.getName()%>"
                       data-component="textbox" required=""/>
            </div>
        </li>


        <li class="form-line" data-type="control_address" id="id_20">
            <label class="form-label form-label-top form-label-auto" id="label_20"> Address </label>
            <div id="cid_20" class="form-input-wide">
                <table summary="" class="form-address-table" cellpadding="0" cellspacing="0">
                    <tbody>
                    <tr>
                        <td colspan="2">
                          <span class="form-sub-label-container" style="vertical-align:top;">
                            <input type="text" id="input_20_addr_line1" name="street"
                                   class="form-textbox form-address-line" value="<%=hb.getStreet()%>" data-component="address_line_1"/>
                            <label class="form-sub-label" for="input_20_addr_line1" id="sublabel_20_addr_line1"
                                   style="min-height:13px;"> Street Address </label>
                          </span>
                        </td>
                    </tr>
                    <tr>
                        <td width="50%">
                              <span class="form-sub-label-container" style="vertical-align:top;">
                                <input type="text" id="input_20_city" name="city"
                                       class="form-textbox form-address-city" size="21" value="<%=hb.getCity()%>" data-component="city"/>
                                <label class="form-sub-label" for="input_20_city" id="sublabel_20_city" style="min-height:13px;"> City </label>
                              </span>
                        </td>
                        <td>
                                  <span class="form-sub-label-container" style="vertical-align:top;">
                                            <input type="text" id="input_20_state" name="state"
                                                   class="form-textbox form-address-state" size="22" value="<%=hb.getState()%>" data-component="state"/>
                                            <label class="form-sub-label" for="input_20_state" id="sublabel_20_state" style="min-height:13px;"> State / Province </label>
                                  </span>
                        </td>
                    </tr>
                    <tr>
                        <td width="50%">
                              <span class="form-sub-label-container" style="vertical-align:top;">
                                <input type="text" id="input_20_postal" name="zipcode"
                                       class="form-textbox form-address-postal" size="10" value="<%=hb.getZipcode()%>" data-component="zip"/>
                                <label class="form-sub-label" for="input_20_postal" id="sublabel_20_postal"
                                       style="min-height:13px;"> Postal / Zip Code </label>
                              </span>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </li>


        <li class="form-line" data-type="control_textarea" >
            <label class="form-label form-label-top form-label-auto"  >
                Please describe the house:
            </label>
            <div class="form-input-wide">
                <textarea  class="form-textarea" name="description" cols="40" rows="6" data-component="textarea" ><%=hb.getDescription()%></textarea>
            </div>
        </li>

        <li class="form-line" data-type="control_textarea" >
            <label class="form-label form-label-top form-label-auto"  >
                Please describe the space around(optional):
            </label>
            <div class="form-input-wide">
                <textarea  class="form-textarea" name="space" cols="40" rows="6" data-component="textarea" ><%=hb.getSpace()%></textarea>
            </div>
        </li>

        <li class="form-line" data-type="control_textarea" >
            <label class="form-label form-label-top form-label-auto"  >
                Please put some notes here:
            </label>
            <div class="form-input-wide">
                <textarea  class="form-textarea" name="notes" cols="40" rows="6" data-component="textarea" ><%=hb.getNotes()%></textarea>
            </div>
        </li>

        <li class="form-line" data-type="control_textarea" >
            <label class="form-label form-label-top form-label-auto"  >
                Please describe the transit:
            </label>
            <div class="form-input-wide">
                <textarea  class="form-textarea" name="transit" cols="40" rows="6" data-component="textarea"><%=hb.getTransit()%></textarea>
            </div>
        </li>


        <li class="form-line" data-type="control_textarea" >
            <label class="form-label form-label-top form-label-auto"  >
                Please describe the Access:
            </label>
            <div class="form-input-wide">
                <textarea  class="form-textarea" name="access" cols="40" rows="6" data-component="textarea" ><%=hb.getAccess()%>"</textarea>
            </div>
        </li>

        <li class="form-line" data-type="control_textarea" >
            <label class="form-label form-label-top form-label-auto"  >
                Please describe the interaction:
            </label>
            <div class="form-input-wide">
                <textarea  class="form-textarea" name="interaction" cols="40" rows="6" data-component="textarea"><%=hb.getInteraction()%></textarea>
            </div>
        </li>

        <li class="form-line" data-type="control_textarea" >
            <label class="form-label form-label-top form-label-auto"  >
                Please describe the house-rules:
            </label>
            <div class="form-input-wide">
                <textarea  class="form-textarea" name="house_rules" cols="40" rows="6" data-component="textarea" ><%=hb.getHouse_rules()%></textarea>
            </div>
        </li>


        <li class="form-line jf-required" data-type="control_textbox" >
            <label class="form-label form-label-top form-label-auto" >
                The property type( apartment/house/ect ):
            </label>
            <div class="form-input-wide jf-required">
                <input type="text"  name="property_type" data-type="input-textbox" class="form-textbox" size="48" value="<%=hb.getProperty_type()%>" data-component="textbox" />
            </div>
        </li>


        <li class="form-line" data-type="control_radio" >
            <label class="form-label form-label-top form-label-auto" > room type </label>
            <div  class="form-input-wide">
                <div class="form-single-column" data-component="radio">
                    <span class="form-radio-item" style="clear:left;">
                      <span class="dragger-item">
                      </span>
                      <input type="radio" class="form-radio" name="room_type" value="0" <%=hb.checked(0)%> />
                      <label  > Private Room </label>
                    </span>
                    <span class="form-radio-item" style="clear:left;">
                      <span class="dragger-item">
                      </span>
                      <input type="radio" class="form-radio" name="room_type" value="1" <%=hb.checked(1)%>/>
                      <label > Entire Home </label>
                    </span>
                    <span class="form-radio-item" style="clear:left;">
                      <span class="dragger-item">
                      </span>
                      <input type="radio" class="form-radio" name="room_type" value="2" <%=hb.checked(2)%>/>
                      <label > Shared Room </label>
                    </span>
                </div>
            </div>
        </li>




        <li class="form-line jf-required" data-type="control_phone">

            <div id="cid_21" class="form-input jf-required">
                <div data-wrapper-react="true">

            <span class="form-sub-label-container" style="vertical-align:top;">
              <label class="form-sub-label"  style="min-height:13px;"> Bedroom Number: </label>
              <input type="number"  class="form-textbox" size="3"  value="<%=hb.getBedrooms()%>"  name ="bedrooms" />
            </span>
                    <span class="form-sub-label-container" style="vertical-align:top;">
              <label class="form-sub-label"  style="min-height:13px;" name="bathrooms"> Bathroom Number: </label>
              <input type="number"  class="form-textbox" size="3" value="<%=hb.getBathrooms()%>" name="bathrooms" />
            </span>
                    <span class="form-sub-label-container" style="vertical-align:top;">
              <label class="form-sub-label"   style="min-height:13px;" > Bed Number:</label>
              <input type="number"  class="form-textbox" size="3" value="<%=hb.getBeds()%>" name="beds" />
            </span>
                </div>
            </div>
        </li>


        <li class="form-line jf-required" data-type="control_phone" id="id_21">

            <div  class="form-input jf-required">
                <div data-wrapper-react="true">

            <span class="form-sub-label-container" style="vertical-align:top;">
              <label class="form-sub-label"  style="min-height:13px;"> People include: </label>
              <input type="number"  class="form-textbox" size="3" value="<%=hb.getGuests_includes()%>"  required="" name ="accommodates" />
            </span>
                    <span class="form-sub-label-container" style="vertical-align:top;">
              <label class="form-sub-label"  style="min-height:13px;" > Price: </label>
              <input type="number"  class="form-textbox" size="3" value="<%=hb.getPrice()%>" name="price"/>
            </span>
                    <span class="form-sub-label-container" style="vertical-align:top;">
              <label class="form-sub-label"   style="min-height:13px;" >Security deposites: </label>
              <input type="number"  class="form-textbox" size="3" value="<%=hb.getSecurity_deposites()%>"  name="security_deposites" />
            </span>
                </div>
            </div>
        </li>
        <li class="form-line jf-required" data-type="control_phone" id="id_21">
        <input type="button" value="get Price" onclick="ajaxSubmit()">
        </li>









            <%}%>



