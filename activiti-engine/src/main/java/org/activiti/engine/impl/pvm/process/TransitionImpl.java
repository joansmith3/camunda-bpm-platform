/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.engine.impl.pvm.process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.delegate.EventListener;




/**
 * @author Tom Baeyens
 */
public class TransitionImpl extends ProcessElementImpl implements PvmTransition {

  private static final long serialVersionUID = 1L;
  
  protected ActivityImpl source;
  protected ActivityImpl destination;
  protected List<EventListener> eventListeners;

  public TransitionImpl(String id, ProcessDefinitionImpl processDefinition) {
    super(id, processDefinition);
  }

  public ActivityImpl getSource() {
    return source;
  }

  public void setDestination(ActivityImpl destination) {
    this.destination = destination;
    destination.getIncomingTransitions().add(this);
  }
  
  public void addEventListener(EventListener eventListener) {
    if (eventListeners==null) {
      eventListeners = new ArrayList<EventListener>();
    }
    eventListeners.add(eventListener);
  }

  public String toString() {
    return "("+source.getId()+")--"+(id!=null?id+"-->(":">(")+destination.getId()+")";
  }

  @SuppressWarnings("unchecked")
  public List<EventListener> getEventListeners() {
    if (eventListeners==null) {
      return Collections.EMPTY_LIST;
    }
    return eventListeners;
  }

  // getters and setters //////////////////////////////////////////////////////

  protected void setSource(ActivityImpl source) {
    this.source = source;
  }

  public ActivityImpl getDestination() {
    return destination;
  }
  
  public void setEventListeners(List<EventListener> eventListeners) {
    this.eventListeners = eventListeners;
  }
}
